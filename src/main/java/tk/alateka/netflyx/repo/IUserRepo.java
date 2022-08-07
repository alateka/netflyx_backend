package tk.alateka.netflyx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.alateka.netflyx.model.User;

public interface IUserRepo extends JpaRepository<User, Integer>{

}
