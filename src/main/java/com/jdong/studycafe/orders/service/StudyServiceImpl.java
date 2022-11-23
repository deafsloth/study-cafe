package com.jdong.studycafe.orders.service;

import com.jdong.studycafe.beverages.domain.Beverage;
import com.jdong.studycafe.beverages.exception.BeverageNotFoundException;
import com.jdong.studycafe.beverages.repository.BeverageRepository;
import com.jdong.studycafe.cafes.domain.Cafe;
import com.jdong.studycafe.cafes.exception.CafeNotFoundException;
import com.jdong.studycafe.cafes.repository.CafeRepository;
import com.jdong.studycafe.members.domain.Member;
import com.jdong.studycafe.members.exception.MemberNotFoundException;
import com.jdong.studycafe.members.repository.MemberRepository;
import com.jdong.studycafe.orders.domain.Study;
import com.jdong.studycafe.orders.dto.OrderRequestDTO;
import com.jdong.studycafe.orders.dto.StudyDTO;
import com.jdong.studycafe.orders.exception.IsStudyingException;
import com.jdong.studycafe.orders.exception.StudyRecordDuplicateException;
import com.jdong.studycafe.orders.repository.StudyQuerydslRepository;
import com.jdong.studycafe.orders.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.jdong.studycafe.orders.domain.QStudy.study;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {
    private final StudyRepository studyRepository;
    private final StudyQuerydslRepository studyQuerydslRepository;
    private final CafeRepository cafeRepository;
    private final MemberRepository memberRepository;
    private final BeverageRepository beverageRepository;

    @Override
    public StudyDTO postPremiunStudy(OrderRequestDTO orderRequestDTO, Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        List<StudyDTO> studyByMemberId = studyQuerydslRepository.findStudyByMemberId(memberId);
        if (studyByMemberId.size() == 1) {
            StudyDTO study = studyByMemberId.get(0);
            if (now.isBefore(study.getEndTime())) {
                throw new IsStudyingException(study.getMemberId().toString());
            } else {
                studyRepository.deleteById(study.getStudyId());
                Optional<Member> optionalMember = memberRepository.findById(memberId);
                Member member = optionalMember.orElseThrow(()->new MemberNotFoundException(memberId));
                Optional<Cafe> optionalCafe = cafeRepository.findById(orderRequestDTO.getCafeId());
                Cafe cafe = optionalCafe.orElseThrow(() -> new CafeNotFoundException(orderRequestDTO.getCafeId()));
                Optional<Beverage> optionalBeverage = beverageRepository.findById(orderRequestDTO.getBeverageId());
                Beverage beverage = optionalBeverage.orElseThrow(() -> new BeverageNotFoundException(orderRequestDTO.getBeverageId()));

                Study saveStudy = Study.builder()
                        .member(member)
                        .beverage(beverage)
                        .cafe(cafe)
                        .chargedTime(orderRequestDTO.getChargedTime())
                        .startTime(now)
                        .endTime(now.plusMinutes(orderRequestDTO.getChargedTime()))
                        .build();
                Study save = studyRepository.save(saveStudy);

                StudyDTO result = StudyDTO.builder()
                        .studyId(saveStudy.getId())
                        .memberId(saveStudy.getMember().getId())
                        .cafeId(saveStudy.getCafe().getId())
                        .cafeName(saveStudy.getCafe().getName())
                        .beverageId(saveStudy.getBeverage().getId())
                        .beverageName(saveStudy.getBeverage().getName())
                        .chargedTime(saveStudy.getChargedTime())
                        .startTime(saveStudy.getStartTime())
                        .endTime(saveStudy.getEndTime())
                        .build();
                return result;
            }
        } else {
            Optional<Member> optionalMember = memberRepository.findById(memberId);
            Member member = optionalMember.orElseThrow(()->new MemberNotFoundException(memberId));
            Optional<Cafe> optionalCafe = cafeRepository.findById(orderRequestDTO.getCafeId());
            Cafe cafe = optionalCafe.orElseThrow(() -> new CafeNotFoundException(orderRequestDTO.getCafeId()));
            Optional<Beverage> optionalBeverage = beverageRepository.findById(orderRequestDTO.getBeverageId());
            Beverage beverage = optionalBeverage.orElseThrow(() -> new BeverageNotFoundException(orderRequestDTO.getBeverageId()));

            Study saveStudy = Study.builder()
                    .member(member)
                    .beverage(beverage)
                    .cafe(cafe)
                    .chargedTime(orderRequestDTO.getChargedTime())
                    .startTime(now)
                    .endTime(now.plusMinutes(orderRequestDTO.getChargedTime()))
                    .build();
            Study save = studyRepository.save(saveStudy);

            StudyDTO result = StudyDTO.builder()
                    .studyId(saveStudy.getId())
                    .memberId(saveStudy.getMember().getId())
                    .cafeId(saveStudy.getCafe().getId())
                    .cafeName(saveStudy.getCafe().getName())
                    .beverageId(saveStudy.getBeverage().getId())
                    .beverageName(saveStudy.getBeverage().getName())
                    .chargedTime(saveStudy.getChargedTime())
                    .startTime(saveStudy.getStartTime())
                    .endTime(saveStudy.getEndTime())
                    .build();
            return result;
        }
    }

    @Override
    public StudyDTO getStudyByMemberId(Long memberId) {
        return null;
    }

    @Override
    public StudyDTO delStudyByMemberId(Long memberId) {
        return null;
    }

    @Override
    public Boolean isStudying(Long memberId) {
        LocalDateTime now = LocalDateTime.now();
        List<StudyDTO> studyByMemberId = studyQuerydslRepository.findStudyByMemberId(memberId);

        if (studyByMemberId.size() == 1) {
            StudyDTO study = studyByMemberId.get(0);
            if (now.isBefore(study.getEndTime())) {
                return Boolean.TRUE;
            } else {
                studyRepository.deleteById(study.getStudyId());
                return Boolean.FALSE;
            }
        } else if (studyByMemberId.size() == 0) {
            return Boolean.FALSE;
        } else {
//            studyByMemberId.stream().map(a -> a.getStudyId()).forEach(x -> studyRepository.deleteById(x));
            studyRepository.deleteAllByMemberId(memberId);
            throw new StudyRecordDuplicateException(memberId.toString());

        }
    }
}
