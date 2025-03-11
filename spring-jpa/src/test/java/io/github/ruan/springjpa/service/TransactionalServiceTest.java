package io.github.ruan.springjpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionalServiceTest {

    @Autowired
    TransactionalService transactionalService;

    @Test
    void saveWithCommitTest() {
        transactionalService.insertAuthorAndBookWithCommit();
    }

    @Test
    void saveWithRollbackTest() {
        transactionalService.insertAuthorAndBookWithRollback();
    }

    @Test
    void updateBookWithoutMethodSaveTest() {
        transactionalService.updateBookWithoutMethodSave();
    }
}