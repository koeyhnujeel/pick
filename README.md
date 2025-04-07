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

## 선착순 쿠폰 발급
![image](https://github.com/user-attachments/assets/00bf7848-b821-4e84-8714-7390913eda40)

💨 **문제 파악** 
- Spring Framework는 기본적으로 멀티스레드를 지원하므로 경쟁 상태에 대비해야 합니다.
- 순간적으로 많은 요청이 발생할 경우, 쿠폰 발급 시 동시성 문제가 발생할 수 있습니다.
- 쿠폰 데이터를 저장하는 과정에서 데이터베이스에 급격한 부하가 발생하여 다른 서비스에 영향을 미칠 수 있습니다.

**💨 해결 과정**
- 이벤트 시간 및 최대 발급 개수 정보를 위한 Event와 Coupon 객체는 로컬 캐시(Caffeine)로 캐싱
    - 분산 환경이지만 데이터 정합성에 문제가 없는 데이터이므로 로컬 캐시를 선택했습니다.
- 중복 발급 검증 및 쿠폰 재고 검증은 글로벌 캐시(Redis)를 통해 처리
    - Redis는 기본적으로 싱글 스레드 방식으로 동작하므로 경쟁 상태가 발생하지 않습니다.
    - Redis의 Set 자료 구조를 활용하여 쿠폰의 중복 발급을 방지합니다. add() 메서드는 값이 없으면 1, 존재하면 0을 반환합니다.
    - Redis INCR 명령어를 활용하여 발급된 쿠폰 수에 대한 정합성을 관리합니다.
- Redis Queue를 활용하여 쿠폰 저장 시 즉시 처리하지 않고 큐에 담아 순차적으로 처리함으로써 시스템 안정성 향상

**💨 결과**
- 동시성 문제 해결
- DB 부하 감소 및 시스템 안정성 향상
<br/>

## 상품 목록 캐싱
![image](https://github.com/user-attachments/assets/13280ac0-af6e-465e-9d54-45b75b424121)

**💨 문제 파악**
- 사용자가 서비스에 접속할 때 가장 먼저 보게 되는 상품 목록으로 인해 데이터베이스 조회가 빈번히 발생합니다.
- 이로 인한 데이터베이스 부하는 응답 속도를 저하시켜 사용자 경험에 부정적인 영향을 미칩니다.

**💨 해결 과정**
- 스케일 아웃과 로드 밸런싱을 적용
- 로컬(Caffeine) 캐시를 활용하여 상품 목록 캐싱
    - 개인화된 상품 목록을 지원하지 않으며, 빈번한 업데이트가 필요한 데이터가 없습니다.
    - 서버 간 데이터 정합성 유지가 비교적 용이할 것으로 판단하여 로컬 캐시를 선택했습니다.
- 상품 정보 업데이트 발생 시, Redis Pub/Sub를 활용하여 다른 서버 인스턴스에 캐시 무효화 전파
    - 예: 각 서버가 자신을 리스너로 설정하고 "productChannel" 토픽을 구독합니다.
    - 상품 정보가 업데이트(추가, 수정, 삭제)되면 productChannel로 메시지를 발행합니다.
    - 구독자가 메시지를 수신한 후 해당 캐시를 삭제합니다.
    

**💨 결과**
- 테스트 도구: nGrinder / 가상 사용자 수: 1,000 / 테스트 시간: 2분
- 평균 응답 시간: 383ms에서 58ms로 약 85% 개선
- 초당 처리량(TPS): 1,750에서 6,800으로 약 290% 향상
<br/>

## ERD
![pick ERD](https://github.com/user-attachments/assets/51b89141-e5ff-49e6-b071-6c2151fd84de)
