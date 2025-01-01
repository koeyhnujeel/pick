![image](https://github.com/user-attachments/assets/99e4300d-c1b4-477f-9627-ec1d98564ea9)

- Sprig boot Server: NCP Server ubuntu-20.04 2대
- Nginx Server: NCP Server ubuntu-20.04 1대
- Mysql Server: NCP Server ubuntu-20.04 1대
- Redis Server: NCP Server ubuntu-20.04 1대
- ETC: Object Storage, Image Optimizer, global CDN
<br/>

## 서비스 동작 및 배포 프로세스
#### 1. 개발자 → GitHub
-	코드를 작성한 후 GitHub에 소스 코드를 업로드합니다.
#### 2. GitHub → GitHub Actions
-	GitHub에 코드가 푸시되면 GitHub Actions가 트리거되어 CI/CD 파이프라인을 실행합니다.
-	GitHub Actions는 소스 코드를 테스트하고, 성공적으로 테스트를 통과하면 Docker 이미지를 빌드합니다.
#### 3. GitHub Actions → Docker Hub
-	빌드된 Docker 이미지를 Docker Hub에 업로드하여 저장합니다.
#### 4. GitHub Actions → App Servers
-	GitHub Actions는 각 App Server에 SSH로 연결하여 Docker 이미지를 가져오고 컨테이너를 실행합니다.
#### 5. 사용자 → Nginx
-	요청은 Nginx를 통해 로드 밸런싱되며, 두 개의 App Server 중 하나로 전달됩니다.(라운드 로빈 방식)
#### 6. Nginx → App Servers
-	Nginx는 로드 밸런서를 사용해 요청을 두 개의 App Server로 분산합니다.
#### 7. App Servers → MySQL
-	각 App Server는 데이터베이스 요청을 MySQL 서버로 전달하여 데이터를 저장하거나 조회합니다.
#### 8. App Servers → Redis
-	App Server는 Redis를 사용하여 데이터 처리를 수행합니다.
#### 9. App Servers → Object Storage
-	사용자가 업로드한 이미지는 App Server를 통해 Object Storage에 저장됩니다.
<br/>

## 프로젝트 소개
- 쇼핑몰 프로젝트 Pick입니다.
- 클라우드 배포 경험을 쌓고자 하였습니다.
- 쿠폰 발급 상황에 대한 동시성 처리를 목표로 하였습니다.
- 대규모 트래픽을 효과적으로 처리할 수 있는 서비스를 구축하는 것을 목표로 하였습니다.
<br/>

## 사용 기술
- Java 17
- Spring Boot 3.3.4
- JPA
- MySQL
- Redis
- Caffeine
<br/>

## ERD
![pick ERD](https://github.com/user-attachments/assets/51b89141-e5ff-49e6-b071-6c2151fd84de)
