package com.be.redditclone.repository;


import com.be.redditclone.model.Role;
import com.be.redditclone.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRoleType(RoleType roleType);
}
