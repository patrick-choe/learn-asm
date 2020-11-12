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

object Abstract : ClassLoader() {
    @JvmStatic
    fun createAbstract(): Class<*> {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_MAXS)
        var methodVisitor: MethodVisitor

        classWriter.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER + Opcodes.ACC_ABSTRACT,
                "com/github/patrick/learnasm/build/Abstract",
                null,
                "java/lang/Object",
                null
        )

        // <init>
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

        // sans
        run {
            methodVisitor = classWriter.visitMethod(
                    Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT,
                    "sans",
                    "()Ljava/lang/String;",
                    null,
                    null
            )

            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }

        val bytes = classWriter.toByteArray()

        return defineClass(
                "com.github.patrick.learnasm.build.Abstract",
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
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                "com/github/patrick/learnasm/build/AbstractImplementation",
                null,
                "com/github/patrick/learnasm/build/Abstract",
                null
        )

        // <init>
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
                    "com/github/patrick/learnasm/build/Abstract",
                    "<init>",
                    "()V",
                    false
            )

            methodVisitor.visitInsn(Opcodes.RETURN)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }

        // sans
        run {
            methodVisitor = classWriter.visitMethod(
                    Opcodes.ACC_PUBLIC,
                    "sans",
                    "()Ljava/lang/String;",
                    null,
                    null
            )

            methodVisitor.visitAnnotation(
                    "Ljava.lang.Override;",
                    false
            )

            methodVisitor.visitLdcInsn("Sans")
            methodVisitor.visitInsn(Opcodes.ARETURN)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }

        val bytes = classWriter.toByteArray()

        return defineClass(
                "com.github.patrick.learnasm.build.AbstractImplementation",
                bytes,
                0,
                bytes.count()
        )
    }
}