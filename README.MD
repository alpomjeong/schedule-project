# 배경
본 프로젝트는 Spring을 통한 CRUD 실습을 위한 일정 앱 구현을 다룹니다.

# 프로젝트 구조


## ScheduleController.java 
클라이언트 요청을 받아 일정(Schedule)을 생성, 조회, 수정, 삭제하는 프레젠테이션 계층(Controller) 역할을 함.
## ScheduleRequestDto.java
클라이언트가 일정(Schedule) 데이터를 생성하거나 수정할 때 사용하는 데이터 전송 객체(DTO) 역할을 함.
요청 데이터를 Controller에서 Service로 전달하는 데 사용됨.
## ScheduleResponseDto.java
클라이언트에게 일정(Schedule) 데이터를 응답할 때 사용하는 데이터 전송 객체(DTO) 역할을 함.
Entity 객체를 기반으로 필요한 정보만 가공하여 Controller에서 반환하는 데 사용됨.


## ScheduleService.java
일정(Schedule)의 생성, 조회, 수정, 삭제 등 비즈니스 로직을 정의하는 서비스 계층의 인터페이스 역할을 함.
구현체에서 실제 로직을 작성하며, Controller에서 호출되어 데이터 처리를 담당함.
## ShceduleServiceImpl.java
비즈니스 로직을 구현하는 서비스 계층(Service Layer) 클래스로,
ScheduleRepository를 사용해 일정 데이터를 처리하고, Controller에서 호출하여 응답을 반환하는 역할을 함.

## Schedule.java 
데이터베이스의 일정(Schedule) 테이블과 매핑되는 도메인(Entity) 클래스로,
일정의 ID, 내용(todo), 작성자(author), 비밀번호(password), 생성 및 수정 시간을 포함하는 객체를 정의함.

## ScheduleRepository.java 
데이터베이스와 직접 상호작용하는 데이터 액세스 계층(Repository Layer) 인터페이스로,
일정(Schedule)의 생성, 조회, 수정, 삭제 등의 메서드를 정의하며, 구현체에서 실제 DB 처리 로직을 작성함.
## JdbcTemplateScheduleRepository.java 
JDBC를 활용하여 데이터베이스와 직접 상호작용하는 데이터 액세스 계층(Repository Layer) 구현체 역할을 함.
SQL을 사용해 일정(Schedule) 데이터를 저장, 조회, 수정, 삭제하는 기능을 수행함.

## ScheduleApplication 
앱의 진입점

| 기능           | method | url             | request                                                                                      | response                                                                                                                                                                                                                                                                                                                          | 상태 코드        |
|----------------|--------|-----------------|----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| 일정 생성      | POST   | /schedules      | RequestBody,  {  "todo": "회의 준비",   "author": "홍길동",   "password": "securepassword" } | {   "id": 1,   "todo": "회의 준비",   "author": "홍길동",   "created_at": "2024-03-24T12:00:00Z",   "updated_at": "2024-03-24T12:00:00Z" }                                                                                                                                                                                        | 200/생성 ok    |
| 일정 단건 조회 | GET    | /schedules/{id} |                                                                                              | {   "id": 1,   "todo": "회의 준비",   "author": "홍길동",   "created_at": "2024-03-24T12:00:00Z",   "updated_at": "2024-03-24T12:00:00Z" }                                                                                                                                                                                        | 200/단건 조회 ok |
| 일정 목록 조회 | GET    | /schedules      |                                                                                              | [   {     "id": 1,     "todo": "회의 준비",     "author": "홍길동",     "created_at": "2024-03-24T12:00:00Z",     "updated_at": "2024-03-24T12:00:00Z"   },   {     "id": 2,     "todo": "프로젝트 보고서 작성",     "author": "김철수",     "created_at": "2024-03-24T14:00:00Z",     "updated_at": "2024-03-24T14:00:00Z"   } ] | 200/목록 조회 ok |
| 일정 수정      | PUT    | /schedules/{id} | RequestBody, {   "todo": "회의 장소 변경",   "password": "securepassword" }                  | {   "id": 1,   "todo": "회의 장소 변경",   "author": "홍길동",   "created_at": "2024-03-24T12:00:00Z",   "updated_at": "2024-03-24T15:00:00Z" }                                                                                                                                                                                   | 200/수정 ok    |
| 일정 삭제      | DELETE | /schedules/{id} | RequestBody, {   "password": "securepassword" }                                              | {   "message": "일정이 삭제되었습니다." }                                                                                                                                                                                                                                                                                         | 200/ 삭제 ok   |


![Image](https://github.com/user-attachments/assets/3c70e27b-2a0c-4442-9d0d-ac08db326303)
