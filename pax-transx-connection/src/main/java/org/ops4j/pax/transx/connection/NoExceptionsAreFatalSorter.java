/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.ops4j.pax.transx.connection;

/**
 * Implementation of a generic @{link ExceptionSorter} that indicates that
 * no Exceptions are fatal. USE THIS ONLY FOR IN-VM DATABASES! or you may be sorry...
 *
 * @version $Revision: 805 $ $Date: 2010-11-11 15:06:35 -0800 (Thu, 11 Nov 2010) $
 */
public class NoExceptionsAreFatalSorter implements ExceptionSorter {

    public static final NoExceptionsAreFatalSorter INSTANCE = new NoExceptionsAreFatalSorter();

    /**
     * Always returns false.
     *
     * @param e the Exception to inspect
     * @return false indicating no Exceptions are fatal
     */
    public boolean isExceptionFatal(Exception e) {
        return false;
    }

    /**
     * never try to rollback.... we assume the connection is always still valid
     * @return false
     */
    public boolean rollbackOnFatalException() {
        return false;
    }

}