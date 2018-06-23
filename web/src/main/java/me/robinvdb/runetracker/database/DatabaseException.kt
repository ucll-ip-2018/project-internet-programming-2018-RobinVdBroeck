package me.robinvdb.runetracker.database

class DatabaseException : RuntimeException {
    constructor() : super() {}

    constructor(message: String) : super(message) {}

    constructor(message: String, throwable: Throwable) : super(message, throwable) {}

    constructor(throwable: Throwable) : super(throwable) {}
}
