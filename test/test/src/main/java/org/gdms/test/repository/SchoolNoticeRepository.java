package org.gdms.test.repository;

import org.gdms.test.entity.SchoolNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SchoolNoticeRepository extends JpaRepository<SchoolNotice,Integer> {
    @Query("from SchoolNotice order by time desc")
    List<SchoolNotice> getSchoolNotices();

    @Query(value="select * from schoolnotice order by time desc limit 3",nativeQuery=true)
    List<SchoolNotice> getThreeSchoolNotices();

    @Query("from SchoolNotice where id=:id")
    Optional<SchoolNotice> getById(int id);
}
