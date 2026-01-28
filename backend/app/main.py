from fastapi import Depends, FastAPI, HTTPException, status
from sqlalchemy.orm import Session

from . import crud, models, schemas
from .database import Base, engine, get_db

Base.metadata.create_all(bind=engine)

app = FastAPI(title="Talim API")


@app.post("/users", response_model=schemas.UserRead, status_code=status.HTTP_201_CREATED)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    return crud.create_user(db, name=user.name, role=user.role)


@app.post("/groups", response_model=schemas.GroupRead, status_code=status.HTTP_201_CREATED)
def create_group(group: schemas.GroupCreate, db: Session = Depends(get_db)):
    return crud.create_group(db, name=group.name)


@app.post("/groups/{group_id}/members", status_code=status.HTTP_201_CREATED)
def add_member(
    group_id: int,
    payload: schemas.MembershipCreate,
    db: Session = Depends(get_db),
):
    group = db.get(models.Group, group_id)
    user = db.get(models.User, payload.user_id)
    if group is None or user is None:
        raise HTTPException(status_code=404, detail="Group or user not found")
    membership = crud.add_member(db, group_id=group_id, user_id=payload.user_id)
    return {"id": membership.id, "group_id": membership.group_id, "user_id": membership.user_id}


@app.post(
    "/assignments",
    response_model=schemas.AssignmentRead,
    status_code=status.HTTP_201_CREATED,
)
def create_assignment(
    assignment: schemas.AssignmentCreate, db: Session = Depends(get_db)
):
    creator = db.get(models.User, assignment.created_by)
    group = db.get(models.Group, assignment.group_id)
    if creator is None or group is None:
        raise HTTPException(status_code=404, detail="Creator or group not found")
    if creator.role != "teacher":
        raise HTTPException(status_code=400, detail="Only teachers can create assignments")
    return crud.create_assignment(
        db,
        title=assignment.title,
        description=assignment.description,
        due_date=assignment.due_date,
        group_id=assignment.group_id,
        created_by=assignment.created_by,
    )


@app.post(
    "/submissions",
    response_model=schemas.SubmissionRead,
    status_code=status.HTTP_201_CREATED,
)
def create_submission(
    submission: schemas.SubmissionCreate, db: Session = Depends(get_db)
):
    assignment = db.get(models.Assignment, submission.assignment_id)
    student = db.get(models.User, submission.student_id)
    if assignment is None or student is None:
        raise HTTPException(status_code=404, detail="Assignment or student not found")
    if student.role != "student":
        raise HTTPException(status_code=400, detail="Only students can submit work")
    return crud.create_submission(
        db,
        assignment_id=submission.assignment_id,
        student_id=submission.student_id,
        status=submission.status,
        score=submission.score,
    )


@app.get("/groups/{group_id}/leaderboard", response_model=list[schemas.LeaderboardEntry])
def leaderboard(group_id: int, db: Session = Depends(get_db)):
    group = db.get(models.Group, group_id)
    if group is None:
        raise HTTPException(status_code=404, detail="Group not found")
    return [
        schemas.LeaderboardEntry(
            student_id=row.student_id,
            student_name=row.student_name,
            total_score=row.total_score,
        )
        for row in crud.get_group_leaderboard(db, group_id)
    ]
