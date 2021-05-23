# 단축 URL 생성

## 내용

* URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
* 예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro

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

## 개발 환경

* Spring Boot, Redis, Java
