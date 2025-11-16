package com.example.blog.post;

import com.example.blog.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findByOwnerOrderByCreatedAtDesc(User owner, Pageable pageable);

    @Query("""
		select p from Post p
		where (:q is null or :q = '' 
		       or p.title like concat('%', :q, '%') 
		       or p.content like concat('%', :q, '%'))
		order by p.createdAt desc
	""")
    Page<Post> search(@Param("q") String q, Pageable pageable);
}