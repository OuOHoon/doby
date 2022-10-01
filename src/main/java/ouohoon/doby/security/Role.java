package ouohoon.doby.security;

public enum Role {
    ADMIN, // 관리자 권한
    USER, // 일반 사용자 권한 ( 일반 행동 가능 )
    GUEST_USER // 손님 사용자 권한 ( 채팅 금지 )
}
