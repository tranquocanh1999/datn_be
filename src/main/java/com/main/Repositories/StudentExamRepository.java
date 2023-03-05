package com.main.Repositories;

import com.main.Entities.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {
    @Query(value = "SELECT  e.competition_id as id,s.degree as degree FROM giáo viênstudent_exam s left join giáo viênexam e ON s.exam_id = e.id  where e.competition_id IN (:ids)", nativeQuery = true)
    List<Object[]> getDegree(List<UUID> ids);

    StudentExamEntity getByStudentUserUsernameAndExamCompetitionId(String username, UUID id);

    StudentExamEntity findById(UUID id);

    @Query(value = "select t1.id,t1.full_name,t2.degree, t2.status ,t2.exam_id, t2.start_at,t2.end_at from \n" +
            "                                   (select distinct  BIN_TO_UUID(s.id) as id , u.full_name,u.username, BIN_TO_UUID(cl.class_id) as class_id, BIN_TO_UUID(co.class_id) as competition_id\n" +
            "                                   from giáo viênuser u,  student s, student_class cl ,competition co\n" +
            "                                   where s.id=cl.student_id\n" +
            "                                    and u.id=s.user_id \n" +
            "                                     and co.class_id=cl.class_id \n" +
            "                                    and co.id=(:id)\n" +
            "                                    ) as t1 left join (select distinct BIN_TO_UUID(co.id) as id,BIN_TO_UUID(se.id) as exam_id,BIN_TO_UUID(se.student_id) as student_id, se.degree,se.status,e.code, se.start_at,se.end_at\n" +
            "                                   from student_exam se, competition co, exam e\n" +
            "                                   where se.exam_id=e.id and e.competition_id=co.id and co.id=(:id)) as t2 on t2.student_id=t1.id  ", nativeQuery = true)
    List<List<String>> getStudents(UUID id);

    @Query(value = "select distinct se.degree, count(*) as total\n" +
            "\tfrom student_exam se,  exam e\n" +
            "\twhere se.exam_id=e.id and e.competition_id=(:id)\n" +
            "    group by se.degree  order by se.degree asc", nativeQuery = true)
    List<List<String>> getDegree(UUID id);
}
