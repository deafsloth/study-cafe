//package com.jdong.studycafe.orders.dto;
//
//import com.querydsl.core.annotations.QueryProjection;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@Builder
//@NoArgsConstructor
//public class OrderCountDTO {
//
//    private Long cafeId;
//    private String cafeName;
//
//    private Long beverageId;
//    private String beverageName;
//    private String mainImageUrl;
//
//    private Long count;
//
//    @QueryProjection
//    public OrderCountDTO(Long cafeId, String cafeName, Long beverageId, String beverageName, String mainImageUrl, Long count) {
//        this.cafeId = cafeId;
//        this.cafeName = cafeName;
//        this.beverageId = beverageId;
//        this.beverageName = beverageName;
//        this.mainImageUrl = mainImageUrl;
//        this.count = count;
//    }
//}
package com.jdong.studycafe.orders.dto;

        import com.querydsl.core.annotations.QueryProjection;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class OrderCountDTO {

    private Long cafeId;
    private Long beverageId;
    private Long count;

    @QueryProjection
    public OrderCountDTO(Long cafeId, Long beverageId, Long count) {
        this.cafeId = cafeId;
        this.beverageId = beverageId;
        this.count = count;
    }
}
