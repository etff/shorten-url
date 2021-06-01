# 단축 URL 생성

## 내용

* URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
* 예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro

![스크린샷](./assets/screenshot.png)

## 실행 방법

* 별도의 설치는 필요하지않으며, 실행 후 http://localhost:8080 으로 접속하여 단축 URL을 생성할 수 있습니다.

## 요구 사항

- 링크
  - [X] URL 입력폼 제공 및 결과 출력
  - [X] URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
  - [X] 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
  - [X] 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
  - [X] Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.

* Database 사용은 필수 아님

### 개발 방향

- 빠른 조회를 위해 Redis 선택. 또한 Single Thread로 실행되기때문에 동시성 케이스 예방
- 단축 URL 정보를 오래 영속화할 필요가 없다고 판단함
- 키 생성 전략은 MD5 해싱한 값의 SUBSTRING한 8자로 생성. 중복이 발생하는 케이스가 평균적으로 매우 적음.
- 대용량에서 처리를 해야한다면 Zoo Keeper 같은 툴을 통해 DB 의 키 관리를 전담하며, 해당키를 BASE62 인코딩하는 방식으로 전환

## REST API

- 단축 URL 생성

1. POST
  - http://localhost:8080/link
2. Request

```json
{
  "link": "http://www.google.com"
}
```

3. Response

```json
{
  "link": "http://localhost:8080/4170157c",
  "count": 0
}
```

- 단축 URL 호출

1. GET

- http://localhost:8080/4170157c

2. REDIRECTION 처리

## 개발 환경

* Spring Boot, Redis, thymeleaf, jQuery
