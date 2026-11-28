
# Todo Collab

Java와 Spring Boot 기반의 할 일 관리 웹 서비스입니다.

## 📌 프로젝트 소개

할 일의 등록, 조회, 수정, 삭제 및 완료 상태 관리를 제공하는 웹 서비스입니다.

GitHub 기반의 체계적인 개발 협업을 경험하기 위해 시작한 팀 프로젝트입니다.
Issue 기반 작업 관리, 기능별 브랜치 운영, Pull Request, 코드 리뷰 및 테스트를 통해 개발을 진행하고 있습니다.

기능 구현뿐 아니라 계층별 역할 분리, 코드 재사용, 예외 처리 및 테스트를 통해 코드 품질과 서비스 안정성을 높이는 것을 목표로 합니다.

## 🛠 기술 스택

| 구분 | 기술 |
|---|---|
| Language | Java 17 |
| Backend | Spring Boot 4.0.9, Spring MVC, Spring Data JPA |
| Frontend | Thymeleaf, HTML |
| Database | H2 Database |
| Validation | Jakarta Bean Validation |
| Build Tool | Gradle |
| Test | JUnit, AssertJ |
| Library | Lombok |
| Development Tool | VS Code |
| Version Control | Git, GitHub |

## ✨ 주요 기능

| 기능 | 상세 내용 |
|---|---|
| 할 일 등록 | 제목, 내용, 마감일 입력 및 유효성 검증 |
| 목록 조회 | 등록된 할 일 목록 및 완료 상태 확인 |
| 상세 조회 | 할 일의 상세 정보 확인 |
| 할 일 수정 | 기존 데이터 표시 및 제목, 내용, 마감일 수정 |
| 할 일 삭제 | 등록된 할 일 삭제 |
| 완료 상태 변경 | 완료·미완료 상태 전환 및 화면 반영 |

### 기술적 특징

- 계층별 역할 분리 및 DTO를 활용한 요청 데이터 관리
- JPA 변경 감지를 활용한 데이터 수정 및 상태 변경
- 입력값 유효성 검증 및 예외 처리
- 등록일 및 수정일 자동 관리

## 📁 프로젝트 구조

```text
src/
├── main/
│   ├── java/com/todo/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── service/
│   │   └── TodoApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/todos/
│       └── application.properties
└── test/
    └── java/com/todo/
        ├── service/
        │   └── TodoServiceTest.java
        └── TodoApplicationTests.java
```

## 🤝 협업 방식

### GitHub 기반 작업 관리

- Issue를 활용한 요구사항 관리 및 기능별 작업 분담
- 기능별 브랜치 생성 및 Pull Request를 통한 코드 병합
- 팀원 간 코드 리뷰 및 피드백 반영
- 병합 완료 후 작업 브랜치 정리

### 개발 워크플로우

Issue → Branch → Develop → Test → Commit & Push → PR → Code Review → Merge

## 🧪 테스트

JUnit과 AssertJ를 활용한 서비스 계층 자동 테스트와 로컬 웹 테스트를 진행합니다.

- 할 일 등록, 조회, 수정, 삭제 및 완료 상태 변경 검증
- 존재하지 않는 ID에 대한 예외 처리 검증
- JPA 변경 감지를 통한 데이터 반영 여부 확인
- 입력값 유효성 검증 및 웹 화면 동작 확인
- 신규 기능 추가에 따른 기존 기능 회귀 테스트

### 자동 테스트 실행

**Windows**

```powershell
.\gradlew test
```

**macOS / Linux**

```bash
./gradlew test
```

## 🚀 실행 방법

### 실행 환경

- JDK 17
- Git
- 내장 H2 Database 사용 (별도의 DB 설치 및 설정 불필요)

### 1. 저장소 복제

```bash
git clone https://github.com/youngju9/todo-collab.git
cd todo-collab
```

### 2. 애플리케이션 실행

**Windows**

```powershell
.\gradlew bootRun
```

**macOS / Linux**

```bash
./gradlew bootRun
```

### 3. 서비스 접속

http://localhost:8080/todos

※ 인메모리 H2 Database를 사용하므로 애플리케이션 종료 시 저장된 데이터가 초기화됩니다.

## 📈 개발 현황

**MVP 기능 구현 완료 · 추가 개발 논의 중**

- [x] 할 일 등록
- [x] 할 일 목록 조회
- [x] 할 일 상세 조회
- [x] 할 일 수정
- [x] 할 일 삭제
- [x] 할 일 완료 상태 변경

현재 백엔드 핵심 기능 구현 및 검증을 완료했으며,
화면은 기능 검증을 위한 기본 UI로 구성되어 있습니다.
