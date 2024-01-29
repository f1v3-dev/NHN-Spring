# Account API

| 기능    | HTTP Method | API Path | Request Body                                                                                                 | Response Body                            |
|-------|-------------|----------|--------------------------------------------------------------------------------------------------------------|------------------------------------------|
| 회원 등록 | POST        | /account | {"name": "사용자 이름","userId":"사용자 아이디","password":"사용자 비밀번호","email":"사용자 이메일","phoneNumber":"사용자 전화번호"} | {”accountId”: “계정 아이디”,"name": "사용자 이름","userId":"사용자 아이디","password":"사용자 비밀번호","email":"사용자 이메일","phoneNumber":"사용자 전화번호",”statusId”: “상태 번호”}|