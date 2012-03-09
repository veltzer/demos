package com.sharefile.model



import grails.test.mixin.*
import org.junit.*
import com.sharefile.auth.User

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ShareFile)
class ShareFileTests {

    void testConstraints() {
        byte[] fileData = new byte[100]
        def userAdmin = new User(username: 'admin', password: 'admin', enabled: true)
        def existingShareFile = new ShareFile(
                name: "my file 1", size: 100, ext: "jpg", contentType: 'jpg', data: fileData, creator: userAdmin)

        mockForConstraintsTests(ShareFile, [existingShareFile])

        // validation should fail
        def file = new ShareFile()

        assert !file.validate()
        assert "nullable" == file.errors["name"]
        assert "nullable" == file.errors["ext"]
        assert "nullable" == file.errors["contentType"]
        assert "nullable" == file.errors["size"]
        assert "nullable" == file.errors["data"]

        // So let's demonstrate the unique and maxSize constraints
        file = new ShareFile(
                name: "my file 1", size: 100, ext: "jpgjpgjpgjpgjpgjpg", contentType: 'jpg', data: fileData, creator: userAdmin)
        assert !file.validate()
        assert "unique" == file.errors["name"]
        assert "maxSize" == file.errors["ext"]

        // Validation should pass!
        //file = new ShareFile(
        //        name: "my file 2", size: 100, ext: "gif", contentType: "gif", data: fileData, creator: userAdmin)
       // assert file.validate()
    }
}
