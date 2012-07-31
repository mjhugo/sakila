package com.piragua.sakila



import org.junit.*
import grails.test.mixin.*

@TestFor(RentalController)
@Mock(Rental)
class RentalControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rental/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rentalInstanceList.size() == 0
        assert model.rentalInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.rentalInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rentalInstance != null
        assert view == '/rental/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rental/show/1'
        assert controller.flash.message != null
        assert Rental.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rental/list'


        populateValidParams(params)
        def rental = new Rental(params)

        assert rental.save() != null

        params.id = rental.id

        def model = controller.show()

        assert model.rentalInstance == rental
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rental/list'


        populateValidParams(params)
        def rental = new Rental(params)

        assert rental.save() != null

        params.id = rental.id

        def model = controller.edit()

        assert model.rentalInstance == rental
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rental/list'

        response.reset()


        populateValidParams(params)
        def rental = new Rental(params)

        assert rental.save() != null

        // test invalid parameters in update
        params.id = rental.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rental/edit"
        assert model.rentalInstance != null

        rental.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rental/show/$rental.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rental.clearErrors()

        populateValidParams(params)
        params.id = rental.id
        params.version = -1
        controller.update()

        assert view == "/rental/edit"
        assert model.rentalInstance != null
        assert model.rentalInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rental/list'

        response.reset()

        populateValidParams(params)
        def rental = new Rental(params)

        assert rental.save() != null
        assert Rental.count() == 1

        params.id = rental.id

        controller.delete()

        assert Rental.count() == 0
        assert Rental.get(rental.id) == null
        assert response.redirectedUrl == '/rental/list'
    }
}
