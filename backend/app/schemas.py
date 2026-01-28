from typing import Optional

from pydantic import BaseModel, Field


class UserCreate(BaseModel):
    name: str = Field(..., min_length=2, max_length=120)
    role: str = Field(..., pattern="^(teacher|student)$")


class UserRead(BaseModel):
    id: int
    name: str
    role: str

    class Config:
        from_attributes = True


class GroupCreate(BaseModel):
    name: str = Field(..., min_length=2, max_length=120)


class GroupRead(BaseModel):
    id: int
    name: str

    class Config:
        from_attributes = True


class MembershipCreate(BaseModel):
    user_id: int


class AssignmentCreate(BaseModel):
    title: str = Field(..., min_length=2, max_length=200)
    description: Optional[str] = None
    due_date: str = Field(..., description="ISO date string")
    group_id: int
    created_by: int


class AssignmentRead(BaseModel):
    id: int
    title: str
    description: Optional[str]
    due_date: str
    group_id: int
    created_by: int

    class Config:
        from_attributes = True


class SubmissionCreate(BaseModel):
    assignment_id: int
    student_id: int
    status: str = Field(..., pattern="^(submitted|missing)$")
    score: Optional[int] = Field(default=None, ge=0, le=100)


class SubmissionRead(BaseModel):
    id: int
    assignment_id: int
    student_id: int
    status: str
    score: Optional[int]

    class Config:
        from_attributes = True


class LeaderboardEntry(BaseModel):
    student_id: int
    student_name: str
    total_score: int
