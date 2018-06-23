package me.robinvdb.runetracker.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException : RuntimeException {
    constructor() {}
    constructor(e: Throwable) : super(e) {}
}
