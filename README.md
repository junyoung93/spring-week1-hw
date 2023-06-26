# spring-week1-hw

#Use Case
![image](https://github.com/junyoung93/spring-week1-hw/assets/128367271/cffe1e43-6914-4e8a-ad43-84945ae52acb)




#API 명세서
![image](https://github.com/junyoung93/spring-week1-hw/assets/128367271/30aa02b2-b8c0-44db-bef4-82cbf49a95aa)

![image](https://github.com/junyoung93/spring-week1-hw/assets/128367271/7750872e-b6bb-45ed-8f44-5ff0ac53bbe2)


************************************************************************************************************

1. 수정,삭제 API의 request를 어떤 방식으로 사용하였나요? (param, query,body)
   : body를 사용했습니다.

2. 어떤 상황에 어떤방식의 request를 써야하나요?
   : 수정, 삭제 작업시 @PathVariable를 사용해서 /api/board/update/{id} , /api/board/delete/{id}에서 선택한 id에 해당하는 게시물을 수정 및 삭제를 하고
     @RequestParam 을 사용해서 /api/board/update/{id}?pw= , /api/board/delete/{id}?pw= 로 해당 비밀번호가 맞으면 수정 및 삭제를 하도록 구현하였습니다.
     @RequestBody 에노테이션을 사용해서 http body에 json형식으로 파일을 전달할때 BoardRequestDTO로 받아서 데이터를 처리하였습니다.

3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않은가요?
   : API에서 중복없는 식별자를 사용했습니다. 그렇지 못한 부분은 API명세서 부분입니다.
   
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
   : Controller에만 작업을 했습니다. 이후에 Repository 와 Service에 분리를 해서 작업을 해야할 것 같습니다.
