package com.piragua.sakila



import org.junit.*
import grails.test.mixin.*

@TestFor(ActorController)
@Mock(Actor)
class ActorControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/actor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.actorInstanceList.size() == 0
        assert model.actorInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.actorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.actorInstance != null
        assert view == '/actor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/actor/show/1'
        assert controller.flash.message != null
        assert Actor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/actor/list'


        populateValidParams(params)
        def actor = new Actor(params)

        assert actor.save() != null

        params.id = actor.id

        def model = controller.show()

        assert model.actorInstance == actor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/actor/list'


        populateValidParams(params)
        def actor = new Actor(params)

        assert actor.save() != null

        params.id = actor.id

        def model = controller.edit()

        assert model.actorInstance == actor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/actor/list'

        response.reset()


        populateValidParams(params)
        def actor = new Actor(params)

        assert actor.save() != null

        // test invalid parameters in update
        params.id = actor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/actor/edit"
        assert model.actorInstance != null

        actor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/actor/show/$actor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        actor.clearErrors()

        populateValidParams(params)
        params.id = actor.id
        params.version = -1
        controller.update()

        assert view == "/actor/edit"
        assert model.actorInstance != null
        assert model.actorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/actor/list'

        response.reset()

        populateValidParams(params)
        def actor = new Actor(params)

        assert actor.save() != null
        assert Actor.count() == 1

        params.id = actor.id

        controller.delete()

        assert Actor.count() == 0
        assert Actor.get(actor.id) == null
        assert response.redirectedUrl == '/actor/list'
    }
}
