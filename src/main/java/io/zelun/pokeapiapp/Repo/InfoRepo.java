package io.zelun.pokeapiapp.Repo;

import io.zelun.pokeapiapp.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepo extends JpaRepository<Info, String> {
    Info findInfoById(String id);
    Info findInfoByName(String name);
}
