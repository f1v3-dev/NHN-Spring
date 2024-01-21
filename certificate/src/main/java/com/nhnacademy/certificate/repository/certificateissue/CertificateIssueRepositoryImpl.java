package com.nhnacademy.certificate.repository.certificateissue;

import com.nhnacademy.certificate.domain.IssueListDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.QCertificateIssue;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport
        implements CertificateIssueRepositoryCustom {


    public CertificateIssueRepositoryImpl() {
        super(CertificateIssue.class);
    }


    @Override
    public Page<IssueListDto> findPagesByResident(Integer serialNumber, Pageable pageable) {
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;

        List<IssueListDto> issueList = from(certificateIssue)
                .where(certificateIssue.resident.residentSerialNumber.eq(serialNumber))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .select(Projections.constructor(IssueListDto.class,
                        certificateIssue.certificateConfirmationNumber,
                        certificateIssue.certificateTypeCode,
                        certificateIssue.certificateIssueDate))
                .fetch();

        long count = from(certificateIssue)
                .where(certificateIssue.resident.residentSerialNumber.eq(serialNumber))
                .fetchCount();

        return new PageImpl<>(issueList, pageable, count);

    }
}
