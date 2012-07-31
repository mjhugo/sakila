package com.piragua.sakila



import org.junit.*
import grails.test.mixin.*

@TestFor(InventoryController)
@Mock(Inventory)
class InventoryControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/inventory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.inventoryInstanceList.size() == 0
        assert model.inventoryInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.inventoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.inventoryInstance != null
        assert view == '/inventory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/inventory/show/1'
        assert controller.flash.message != null
        assert Inventory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/inventory/list'


        populateValidParams(params)
        def inventory = new Inventory(params)

        assert inventory.save() != null

        params.id = inventory.id

        def model = controller.show()

        assert model.inventoryInstance == inventory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/inventory/list'


        populateValidParams(params)
        def inventory = new Inventory(params)

        assert inventory.save() != null

        params.id = inventory.id

        def model = controller.edit()

        assert model.inventoryInstance == inventory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/inventory/list'

        response.reset()


        populateValidParams(params)
        def inventory = new Inventory(params)

        assert inventory.save() != null

        // test invalid parameters in update
        params.id = inventory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/inventory/edit"
        assert model.inventoryInstance != null

        inventory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/inventory/show/$inventory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        inventory.clearErrors()

        populateValidParams(params)
        params.id = inventory.id
        params.version = -1
        controller.update()

        assert view == "/inventory/edit"
        assert model.inventoryInstance != null
        assert model.inventoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/inventory/list'

        response.reset()

        populateValidParams(params)
        def inventory = new Inventory(params)

        assert inventory.save() != null
        assert Inventory.count() == 1

        params.id = inventory.id

        controller.delete()

        assert Inventory.count() == 0
        assert Inventory.get(inventory.id) == null
        assert response.redirectedUrl == '/inventory/list'
    }
}
