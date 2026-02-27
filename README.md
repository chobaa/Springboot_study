# 🌱 Spring Boot 스터디 프로젝트: 자유 게시판 & 회원 관리 시스템

김영한님의 스프링 입문 강의를 기반으로 진행한 7주차 스터디 프로젝트 코드 저장소입니다.

 **단순한 예제 따라 치기를 넘어, 세션 기반 인증이 적용된 웹 게시판을 직접 설계하고 구현**했습니다.

<br>

## 📚 Detailed Docs (Notion)
학습한 내용의 상세한 필기, 에러 해결 과정, 기술적 의사결정 등은 아래 링크를 통해 확인하실 수 있습니다.

👉 **[Spring Boot Study Board 바로가기](https://www.notion.so/2ebc16bedd3d8000a076d381664503ff?v=2ebc16bedd3d806ba8c1000cbdc5b674&source=copy_link)**

<br>

## 🛠 Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 4.0.1
- **Data Access:** Spring Data JPA
- **Database:** H2 Database
- **Template Engine:** Thymeleaf
- **Frontend:** Bootstrap 5, HTML/CSS

<br>

## ✨ Key Features (핵심 기능)

### 1. 회원 관리 시스템
- **회원 가입 및 로그인:** `HttpSession`을 활용한 세션 기반 인증 구현
- **로그아웃:** 세션 만료 처리를 통한 안전한 로그아웃
- **회원 목록 조회:** 가입된 전체 사용자의 정보를 확인하는 관리자/사용자 공통 뷰

### 2. 게시판 (CRUD)
- **접근 제어:** 로그인한 사용자만 새 글을 작성할 수 있도록 권한 분리
- **게시글 조회:** 작성자(LoginId)와 제목, 내용을 함께 보여주는 리스트 뷰 및 상세 뷰
- **수정 및 삭제 권한 검증:** 현재 세션의 로그인 아이디와 게시글 도메인의 작성자 아이디를 비교하여, 본인이 작성한 글만 수정 및 삭제 가능하도록 예외 처리(`IllegalStateException`) 적용

### 3. AOP (관점 지향 프로그래밍) 적용
- `@Around` 어노테이션을 활용하여 핵심 비즈니스 로직(Controller, Service, Repository)의 수정 없이 **전 계층의 메서드 실행 시간(TimeTrace)을 로깅**하는 기능 구현

<br>

## 📝 Study Goals & Achievements
- Spring Boot의 핵심 동작 원리 이해 (Auto Configuration, DI/IoC)
- 메모리 저장소(`MemoryRepository`)에서 관계형 데이터베이스(`Spring Data JPA`)로의 마이그레이션 경험
- MVC 패턴을 활용한 RESTful API 설계 및 View 연동
- AOP를 활용한 횡단 관심사 분리 및 객체 지향적 설계 실무 적용

<br>