package com.main.Repositories;

import com.main.Entities.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {
    @Query(value = "SELECT  e.competition_id as id,s.degree as degree FROM datn_db.student_exam s left join datn_db.exam e ON s.exam_id = e.id  where e.competition_id IN (:ids)", nativeQuery = true)
    List<Object[]> getDegree(List<UUID> ids);

    StudentExamEntity getByStudentUserUsernameAndExamCompetitionId(String username, UUID id);

    StudentExamEntity findById(UUID id);

    @Query(value = "select t1.id,t1.full_name,t2.degree, t2.status from \n" +
            "(select distinct  BIN_TO_UUID(s.id) as id , u.full_name,u.username, BIN_TO_UUID(cl.class_id) as class_id, BIN_TO_UUID(co.class_id) as competition_id\n" +
            "from datn_db.user u,  student s, student_class cl ,competition co\n" +
            "where s.id=cl.student_id\n" +
            " and u.id=s.user_id \n" +
            "  and co.class_id=cl.class_id \n" +
            " and co.id=(:id)\n" +
            " ) as t1 left join (select distinct BIN_TO_UUID(se.student_id) as student_id, se.degree,se.status,e.code\n" +
            "from student_exam se, competition co, exam e\n" +
            "where se.exam_id=e.id) as t2 on t2.student_id=t1.id\n" +
            " ", nativeQuery = true)
    List<List<String>> getStudents(UUID id);
}
