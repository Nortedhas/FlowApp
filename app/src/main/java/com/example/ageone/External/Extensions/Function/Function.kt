package com.example.ageone.External.Extensions.Function


inline fun <T> T.guard(block: T.() -> Unit): T { if (this == null) block(); return this}