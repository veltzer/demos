package com.sharefile.model

import org.springframework.dao.DataIntegrityViolationException
import com.sharefile.auth.User

class ShareFileController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        def currentUser = getCurrentUser()
        // dynamic finder - my files
        def myFiles = ShareFile.findAllByCreator(currentUser, params)
        // criteria - files shared by others
        def c = ShareFile.createCriteria()
        def shareFiles = c.list(params) {
            users {
                eq('username', currentUser.username)
            }
        }
        [shareFileInstanceList: myFiles, otherFiles: shareFiles]
    }

    def create() {
        [shareFileInstance: new ShareFile(params)]
    }

    def save() {
        def shareFileInstance = new ShareFile(params)
        def uploadedFile = request.getFile('data')
        if (!uploadedFile.empty) {
            //binding data from file
            shareFileInstance.size = uploadedFile.getSize() / 1024
            String filename = uploadedFile.getOriginalFilename()
            shareFileInstance.ext = filename.substring(filename.lastIndexOf(".") + 1)
            shareFileInstance.contentType = uploadedFile.getContentType()
            shareFileInstance.creator = getCurrentUser()
        } else {
            render(view: "create", model: [shareFileInstance: shareFileInstance])
            return
        }
        if (!shareFileInstance.save(flush: true)) {
            render(view: "create", model: [shareFileInstance: shareFileInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    private getCurrentUser() {
        User.get(springSecurityService.getPrincipal().id)
    }


    def show() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def edit() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        [shareFileInstance: shareFileInstance]
    }

    def update() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (shareFileInstance.version > version) {
                shareFileInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'shareFile.label', default: 'ShareFile')] as Object[],
                        "Another user has updated this ShareFile while you were editing")
                render(view: "edit", model: [shareFileInstance: shareFileInstance])
                return
            }
        }

        shareFileInstance.properties = params

        if (!shareFileInstance.save(flush: true)) {
            render(view: "edit", model: [shareFileInstance: shareFileInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), shareFileInstance.id])
        redirect(action: "show", id: shareFileInstance.id)
    }

    def delete() {
        def shareFileInstance = ShareFile.get(params.id)
        if (!shareFileInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
            return
        }

        try {
            shareFileInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shareFile.label', default: 'ShareFile'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    def download() {
        def shareFileInstance = ShareFile.get(params.id)
        if (shareFileInstance) {
            String downloadName = URLEncoder.encode(shareFileInstance.name + '.' + shareFileInstance.ext, 'UTF-8')
            response.setHeader("Content-Disposition", "attachment;filename=${downloadName}")
            response.setContentType("application-xdownload")
            response.getOutputStream() << new ByteArrayInputStream(shareFileInstance.data)
            return
        } else {
            render(view: '/error')
        }
    }
}
