테이블명/주요 컬럼 (Field)/관계(Relationship)/비고

User (유저)/"id(PK), username, email, password, createdAt, updatedAt"/-/비밀번호 8자 이상

Schedule (일정)/"id(PK), user_id(FK), title, content, createdAt, updatedAt"/User(1) : Schedule(N)/작성 유저명 대신 유저 식별자 사용

Comment (댓글)/"id(PK), schedule_id(FK), user_id(FK), content, createdAt, updatedAt"/Schedule(1) : Comment(N) User(1) : Comment(N)

기능/Method/URL/Request Body/상태 코드
회원가입/POST/[/api/users/signup]/"username, email, password"/201 Created
로그인/POST/[/api/users/login,"email]/ password"/200 OK (Session 생성)
유저 조회/GET/[/api/users/{id}]/-/200 OK