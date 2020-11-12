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

package com.github.patrick.learnasm.sources

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

object Interface : ClassLoader() {
    @JvmStatic
    fun main(args: Array<String>) {
        val interfaceClass = createInterface()
        val implementationClass = createImplementation()

        val instance = implementationClass.newInstance()
        val method = interfaceClass.getDeclaredMethod("sans")
        println(method.invoke(instance))
    }

    @JvmStatic
    fun createInterface(): Class<*> {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)

        classWriter.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_INTERFACE + Opcodes.ACC_ABSTRACT,
                "com/github/patrick/learnasm/build/Interface",
                null,
                "java/lang/Object",
                null
        )

        val methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT,
                "sans",
                "()Ljava/lang/String;",
                null,
                null
        )

        methodVisitor.visitMaxs(0, 0)
        methodVisitor.visitEnd()

        val bytes = classWriter.toByteArray()

        return defineClass(
                "com.github.patrick.learnasm.build.Interface",
                bytes,
                0,
                bytes.count()
        )
    }

    @JvmStatic
    fun createImplementation(): Class<*> {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
        var methodVisitor: MethodVisitor

        classWriter.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC,
                "com/github/patrick/learnasm/build/Implementation",
                null,
                "java/lang/Object",
                arrayOf("com/github/patrick/learnasm/build/Interface")
        )

        run {
            methodVisitor = classWriter.visitMethod(
                    Opcodes.ACC_PUBLIC,
                    "<init>",
                    "()V",
                    null,
                    null
            )

            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)

            methodVisitor.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    "java/lang/Object",
                    "<init>",
                    "()V",
                    false
            )

            methodVisitor.visitInsn(Opcodes.RETURN)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }

        run {
            methodVisitor = classWriter.visitMethod(
                    Opcodes.ACC_PUBLIC,
                    "sans",
                    "()Ljava/lang/String;",
                    null,
                    null
            )

            methodVisitor.visitLdcInsn("Sans")
            methodVisitor.visitInsn(Opcodes.ARETURN)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }

        val bytes = classWriter.toByteArray()

        return defineClass(
                "com.github.patrick.learnasm.build.Implementation",
                bytes,
                0,
                bytes.count()
        )
    }
}