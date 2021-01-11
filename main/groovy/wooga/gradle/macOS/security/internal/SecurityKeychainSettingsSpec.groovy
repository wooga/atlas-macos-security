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

package wooga.gradle.macOS.security.internal

import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input

trait SecurityKeychainSettingsSpec extends SecurityKeychainSpec {

    final Property<Boolean> lockKeychainWhenSleep = project.objects.property(Boolean)
    
    void setLockKeychainWhenSleep(Boolean value) {
        lockKeychainWhenSleep.set(value)
    }
    
    void setLockKeychainWhenSleep(Provider<Boolean> value) {
        lockKeychainWhenSleep.set(value)
    }
    
    SecurityKeychainSettingsSpec lockKeychainWhenSleep(Boolean value) {
        setLockKeychainWhenSleep(value)
        this
    }
    
    SecurityKeychainSettingsSpec lockKeychainWhenSleep(Provider<Boolean> value) {
        setLockKeychainWhenSleep(value)
        this
    }
    
    final Property<Integer> lockKeychainAfterTimeout = project.objects.property(Integer)
    
    void setLockKeychainAfterTimeout(Integer value) {
        lockKeychainAfterTimeout.set(value)
    }
    
    void setLockKeychainAfterTimeout(Provider<Integer> value) {
        lockKeychainAfterTimeout.set(value)
    }
    
    SecurityKeychainSettingsSpec lockKeychainAfterTimeout(Integer value) {
        setLockKeychainAfterTimeout(value)
        this
    }
    
    SecurityKeychainSettingsSpec lockKeychainAfterTimeout(Provider<Integer> value) {
        setLockKeychainAfterTimeout(value)
        this
    }

    @Input
    List<String> getKeychainSettingsArguments() {
        def arguments = []
        if(lockKeychainWhenSleep.present && lockKeychainWhenSleep.get()) {
            arguments << "-l"
        }

        if(lockKeychainAfterTimeout.present) {
            arguments << "-u" << "-t" << lockKeychainAfterTimeout.get().toString()
        }
        arguments
    }
}
