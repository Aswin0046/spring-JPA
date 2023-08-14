package com.jdbc.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jdbc.demo.jpaRepo.JPARepository;
import com.jdbc.demo.model.JPAModel;
import com.jdbc.demo.model.Response;

@Service
public class JPAService {

	@PersistenceContext

	EntityManager entityManager;

	Response rsp = new Response();

	@Autowired
	JPARepository jparepo;

	public Response getUser() {

		return rsp;
	}

	public Response createUser(JPAModel values) {

		try {

			String uuid = UUID.randomUUID().toString();
			values.setsNo(uuid);
			values.setCreatedBy(uuid);
			values.setUpdatedBy(uuid);

			jparepo.save(values); // Instead of writing Insert query we are calling .save()

			rsp.setData("User created");
			rsp.setResponseCode(200);
			rsp.setResponseMessage("");
		} catch (Exception e) {
			e.printStackTrace();

			rsp.setData("cannot create");
			rsp.setResponseCode(500);
			rsp.setResponseMessage("Error");

		}

		return rsp;
	}

	public Response updateUser(JPAModel values) {

		try {

			Optional<JPAModel> optObj = jparepo.findById(values.getsNo());

			if (optObj.isPresent()) {

				JPAModel update = optObj.get();
				update.setEmail(values.getEmail());

				jparepo.save(update);

				rsp.setData("User updated");
				rsp.setResponseCode(200);
				rsp.setResponseMessage("Success");

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Failed to update");
			rsp.setResponseCode(500);
			rsp.setResponseMessage("Error");

		}

		return rsp;
	}

	public Response getOneUser() {

		return rsp;
	}

	public Response deleteUser(String sNo) {
		try {

			jparepo.deleteById(sNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsp;
	}

	public Response scamUser() {

		return rsp;
	}

	@SuppressWarnings("unchecked")
	public Response loginUser(String email, String password) {

		try {

			Query query = entityManager
					.createQuery("SELECT u FROM JPAModel u WHERE u.email=:email AND u.password=:password");
			query.setParameter("email", email);
			query.setParameter("password", password); 

			List<JPAModel> values = query.getResultList();

			if (values.isEmpty()) {
				
				rsp.setData("No Such User Found!");
				rsp.setResponseCode(500);
				rsp.setResponseMessage("Error");

			} else {
				rsp.setData("Existing User!");
				rsp.setResponseCode(200);
				rsp.setResponseMessage("Success");
				
			}

		} catch (Exception e) {

			e.printStackTrace();
			rsp.setData("Failed to update");
			rsp.setResponseCode(500);
			rsp.setResponseMessage("Error");

		}

		return rsp;
	}

}
