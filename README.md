# 2023-Team-F-게시판-프로젝트

진행방식
------
- 프론트와 협업을 가정하고 서버 구축만(화면 구현X) 진행한다.
- Spring Boot를 사용한다.
- Spring Data JPA를 활용하며 그 외 라이브러리는 자율적으로 선택한다.
- 빌드 툴은 gradle을 사용한다.
- 일관된 코드 컨벤션과 깃 컨벤션을 준수한다.
- REST API 규격을 준수한다.
- 구현의 이유와 근거는 블로그에 정리하고 모든 과정(개발, 트러블 슈팅)은 기록한다.
- 구현 기능에 없는 내용은 자율적으로 구현하되 모든 구현에 근거를 담는다.

제출 방법 
------
- fork한 repository 최신화 방법
  - git remote add upstream https://github.com/2023-Techeer-Partners-Team-F/Board-Service.git (초기 1회만)
  - git remote -v ( 연결 확인 용도 )


- git fetch upstream ( 원본과 동기화 작업 )
- git merge upstream/main ( 본인 레포에 적용 )

- fork한 저장소의 각 주차에 해당되는 과제 폴더에 소스 코드를 commit 후 push 합니다.

  - commit 메세지는 다음과 같이 합니다. "주차 구현"

    ex) 1주차 구현


- 문제를 다 풀었으면 PR을 올립니다.
  PR 제목은 "[이름] O주차 과제 제출" 로 합니다.
  
  ex) [윤정은] 1주차 과제 제출
