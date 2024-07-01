# 해야할 일
> 1. 채팅방 구현 V
> 2. 채팅 메시지 보낼때 Redis 로 캐싱 V
> 3. 카프카 구현(서버 별 토픽 생성 및 파티션 분할) V
> 4. 멤버 구현 V
> 5. 멤버 전체 조회 기능 추가 ( 친구 기능도 추가해야함 )
> 6. 멤버끼리 채팅방 동적 생성 및 목록 추가
> 7. 로그인한 사용자가 속한 채팅방 전체 조회 및 채팅방 선택 시 /chat 으로 리다이렉트 + /queue + roomId 로 웹소켓 통신해야함


# 일지
> 7월 1일 \
> 1. 채팅 메시지를 보낼때마다 Redis에 캐싱해서 매번 메시지를 보낼때 Select 쿼리를 날리지 않게 했다.(성능 향상)
> 2. 토픽은 서버당 하나만 생성 하는게 좋을거같음.(분산은 파티션 갯수 조절로 가능)
> 3. application-A.yml 같은 방식으로 서버당 각각 application.yml을 설정하면 효율적으로 관리가능 (해야할일) 
> 4. Kafka KRaft(kafka 2.8.0 이후 지원) 모드를 설정하면 zookeeper(브로커를 관리하고 조정) 를 설정하지않고 kafka 자체 메타 데이터 관리 가능
> 5. 멤버 생성 및 로그인 기능 추가(매우 간단, 로그인하면 정보 로컬 스토리지에 저장)
> 6. 초대하기 로직만 만들어둠(테스트 코드 추가 작성 필요 -> TODO::LIST 추가하면서 테스트 겸할 예정) 
> 7. 멤버 전체 조회 기능 추가 ( 친구 기능도 추가해야함 )