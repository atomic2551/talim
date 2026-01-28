from sqlalchemy import func
from sqlalchemy.orm import Session

from . import models


def create_user(db: Session, name: str, role: str) -> models.User:
    user = models.User(name=name, role=role)
    db.add(user)
    db.commit()
    db.refresh(user)
    return user


def create_group(db: Session, name: str) -> models.Group:
    group = models.Group(name=name)
    db.add(group)
    db.commit()
    db.refresh(group)
    return group


def add_member(db: Session, group_id: int, user_id: int) -> models.GroupMembership:
    membership = models.GroupMembership(group_id=group_id, user_id=user_id)
    db.add(membership)
    db.commit()
    db.refresh(membership)
    return membership


def create_assignment(
    db: Session,
    title: str,
    description: str | None,
    due_date: str,
    group_id: int,
    created_by: int,
) -> models.Assignment:
    assignment = models.Assignment(
        title=title,
        description=description,
        due_date=due_date,
        group_id=group_id,
        created_by=created_by,
    )
    db.add(assignment)
    db.commit()
    db.refresh(assignment)
    return assignment


def create_submission(
    db: Session,
    assignment_id: int,
    student_id: int,
    status: str,
    score: int | None,
) -> models.Submission:
    submission = models.Submission(
        assignment_id=assignment_id,
        student_id=student_id,
        status=status,
        score=score,
    )
    db.add(submission)
    db.commit()
    db.refresh(submission)
    return submission


def get_group_leaderboard(db: Session, group_id: int):
    result = (
        db.query(
            models.User.id.label("student_id"),
            models.User.name.label("student_name"),
            func.coalesce(func.sum(models.Submission.score), 0).label("total_score"),
        )
        .join(models.GroupMembership, models.GroupMembership.user_id == models.User.id)
        .outerjoin(
            models.Submission,
            models.Submission.student_id == models.User.id,
        )
        .filter(models.GroupMembership.group_id == group_id)
        .filter(models.User.role == "student")
        .group_by(models.User.id)
        .order_by(func.coalesce(func.sum(models.Submission.score), 0).desc())
        .all()
    )
    return result
