package com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.repository;

import com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
