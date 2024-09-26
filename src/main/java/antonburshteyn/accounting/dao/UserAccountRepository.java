package antonburshteyn.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import antonburshteyn.accounting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
