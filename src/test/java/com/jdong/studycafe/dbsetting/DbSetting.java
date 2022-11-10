package com.jdong.studycafe.dbsetting;

import com.jdong.studycafe.Members.domain.Member;
import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.domain.BeverageImage;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.domain.CafeImage;
import com.jdong.studycafe.cafes.repository.CafeImageRepository;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.common.enumerate.Region;
import com.jdong.studycafe.favorites.repository.FavoriteRepository;
import com.jdong.studycafe.cafes.repository.*;
import com.jdong.studycafe.beverages.repository.BeverageImageRepository;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import com.jdong.studycafe.Members.repository.MemberRepository;
import com.jdong.studycafe.menus.domain.Menu;
import com.jdong.studycafe.menus.repository.MenuRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Log4j2
public class DbSetting {

    @Autowired
    private BeverageImageRepository beverageImageRepository;
    @Autowired
    private BeverageRepository beverageRepository;

    @Autowired
    private CafeRepository cafeRepository;
    @Autowired
    private CafeImageRepository cafeImageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MenuRepository menuRepository;



    @Test
    public void 테스트_디비_세팅() {
        Double longitude = 127.123+(0.01);
        Double latitude = 32.123+(0.01);
        String pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        Point point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Cafe cafeAAA = Cafe.builder()
                .id(1)
                .name("cafeAAA")
                .description("description cafe AAA")
                .location(point)
                .isPremium(true)
                .region(Region.SEOUL)
                .build();
        System.out.println("cafe = " + cafeAAA);
        cafeRepository.save(cafeAAA);

        longitude = 127.123+(0.1);
        latitude = 32.123+(0.1);
        pointWKT = String.format("POINT(%s %s)", longitude, latitude);
        point = null;
        try {
            point = (Point) new WKTReader().read(pointWKT);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Cafe cafeBBB = Cafe.builder()
                .id(2)
                .name("cafeBBB")
                .description("description cafe BBB")
                .location(point)
                .isPremium(false)
                .region(Region.GYEONGGI)
                .build();
        System.out.println("cafe = " + cafeBBB);
        cafeRepository.save(cafeBBB);

        Long cafeid = 1L;
        cafeAAA = Cafe.builder().id(cafeid).build();

        String imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeAAA/cafeAAA01.jpg";
        CafeImage cafeImage01 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeAAA)
                .build();
        cafeImageRepository.save(cafeImage01);

        imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeAAA/cafeAAA02.jpg";
        CafeImage cafeImage02 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeAAA)
                .build();

        cafeImageRepository.save(cafeImage02);


        cafeid = 2L;
        cafeBBB = Cafe.builder().id(cafeid).build();

        imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeBBB/cafeBBB01.jpg";
        cafeImage01 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeBBB)
                .build();
        cafeImageRepository.save(cafeImage01);

        imgUrl="https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/cafe/cafeBBB/cafeBBB02.jpg";
        cafeImage02 = CafeImage.builder()
                .imageUrl(imgUrl)
                .cafe(cafeBBB)
                .build();

        cafeImageRepository.save(cafeImage02);


        Beverage beverage = Beverage.builder()
                .name("Americano")
                .price(4000)
                .isPremium(false)
                .description("this is Americano")
                .mainImageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/americano/americano01.jpg")
                .build();

        beverageRepository.save(beverage);
        beverage = Beverage.builder()
                .name("Cafe Latte")
                .price(5000)
                .isPremium(true)
                .description("this is Cafe Latte")
                .mainImageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/cafelatte/cafelatte01.jpg")
                .build();

        beverageRepository.save(beverage);


        beverage = Beverage.builder().id(1L).build();
        BeverageImage beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/americano/americano01.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);


        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/americano/americano02.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);

        beverage = Beverage.builder().id(2L).build();
        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/cafelatte/cafelatte01.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);


        beverageImage = BeverageImage.builder()
                .imageUrl("https://study-cafe-bucket.s3.ap-northeast-2.amazonaws.com/study-cafe-bucket/beverage/cafelatte/cafelatte02.jpg")
                .beverage(beverage)
                .build();

        beverageImageRepository.save(beverageImage);

        Member member = Member.builder()
                .email("jdonghun01@gmail.com")
                .generalCredit(0)
                .name("장동훈")
                .password("$2a$10$Q6MtKeljShWgVOAxHkTgOewxuMPUuFYzkhTXN8fOaGUdRUlNjNYL6")
                .provider("google")
                .providerId("105138552831166261696")
                .roles("ROLE_USER")
                .specialCredit(0)
                .username("google_105138552831166261696")
                .build();
        memberRepository.save(member);

        cafeAAA = Cafe.builder().id(1L).build();
        cafeBBB = Cafe.builder().id(2L).build();

        Beverage Americano = Beverage.builder().id(1L).build();
        Beverage Latte = Beverage.builder().id(2L).build();

        Menu menu_00 = Menu.builder().cafe(cafeAAA).beverage(Americano).build();
        Menu menu_01 = Menu.builder().cafe(cafeAAA).beverage(Latte).build();
        Menu menu_02 = Menu.builder().cafe(cafeBBB).beverage(Latte).build();

        menuRepository.save(menu_00);
        menuRepository.save(menu_01);
        menuRepository.save(menu_02);

//        Favorite favorite1 = Favorite.builder()
//                .member(Member.builder().id(1L).build())
//                .cafe(Cafe.builder().id(1L).build())
//                .beverage(Beverage.builder().id(2L).build())
//                .build();
//        Favorite favorite2 = Favorite.builder()
//                .member(Member.builder().id(1L).build())
//                .cafe(Cafe.builder().id(2L).build())
//                .beverage(Beverage.builder().id(1L).build())
//                .build();
//        Favorite favorite3 = Favorite.builder()
//                .member(Member.builder().id(1L).build())
//                .cafe(Cafe.builder().id(2L).build())
//                .beverage(Beverage.builder().id(2L).build())
//                .build();
//
//        favoriteRepository.save(favorite1);
//        favoriteRepository.save(favorite2);
//        favoriteRepository.save(favorite3);

    }
}
