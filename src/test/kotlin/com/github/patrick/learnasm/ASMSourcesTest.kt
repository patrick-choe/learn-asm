/*
 * Copyright (C) 2020 PatrickKR
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact me on <mailpatrickkr@gmail.com>
 */

package com.github.patrick.learnasm

import com.github.patrick.learnasm.sources.Abstract
import com.github.patrick.learnasm.sources.HelloWorld
import com.github.patrick.learnasm.sources.Interface
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class ASMSourcesTest {
    @Test
    fun testHelloWorld() {
        assertDoesNotThrow {
            val testOutStream = ByteArrayOutputStream()
            val testErrStream = ByteArrayOutputStream()
            val outStream = System.out
            val errStream = System.err

            System.setOut(PrintStream(testOutStream))
            System.setErr(PrintStream(testErrStream))

            HelloWorld.main(emptyArray())

            assertEquals("Hello World!", testOutStream.toString().trim())

            System.setOut(outStream)
            System.setErr(errStream)
        }
    }

    @Test
    fun testInterface() {
        assertDoesNotThrow {
            val interfaceClass = Interface.createInterface()
            val implementationClass = Interface.createImplementation()

            val instance = implementationClass.newInstance()
            val method = interfaceClass.getDeclaredMethod("sans")

            assertEquals("Sans", method.invoke(instance))
        }
    }

    @Test
    fun testAbstract() {
        assertDoesNotThrow {
            val abstractClass = Abstract.createAbstract()
            val implementationClass = Abstract.createImplementation()

            val instance = implementationClass.newInstance()
            val method = abstractClass.getDeclaredMethod("sans")

            assertEquals("Sans", method.invoke(instance))
        }
    }
}