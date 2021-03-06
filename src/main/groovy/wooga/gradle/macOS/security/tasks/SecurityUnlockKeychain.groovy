/*
 * Copyright 2018-2020 Wooga GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wooga.gradle.macOS.security.tasks

import com.wooga.security.command.UnlockKeychain
import org.gradle.api.Task
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

class SecurityUnlockKeychain extends AbstractInteractiveSecurityTask {
    private final Property<String> password

    @Input
    Property<String> getPassword() {
        password
    }

    void setPassword(String value) {
        password.set(value)
    }

    void setPassword(Provider<String> value) {
        password.set(value)
    }

    private final RegularFileProperty keychain

    @Optional
    @InputFile
    RegularFileProperty getKeychain() {
        keychain
    }

    void setKeychain(File value) {
        keychain.set(value)
    }

    void setKeychain(Provider<RegularFile> value) {
        keychain.set(value)
    }

    SecurityUnlockKeychain() {
        this.password = project.objects.property(String)
        this.keychain = project.objects.fileProperty()

        outputs.upToDateWhen(new Spec<Task>() {
            @Override
            boolean isSatisfiedBy(Task task) {
                false
            }
        })
    }

    @TaskAction
    void unlock() {
        def command = new UnlockKeychain().withPassword(password.get())
        if (keychain.isPresent()) {
            command.withKeychain(keychain.get().asFile)
        }
        command.execute()
    }


}
