/*
 * The MIT License
 *
 * Copyright 2016  jesse.zwd@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jessezhu.starriver.util;

import java.util.Date;

public interface Clock {

    static final Clock Default = new DefaultClock();

    Date getCurrentDate();

    Long getCurrentTimeInMillis();

    public static class DefaultClock implements Clock {

        @Override
        public Date getCurrentDate() {
            return new Date();
        }

        @Override
        public Long getCurrentTimeInMillis() {
            return System.currentTimeMillis();
        }

    }

    /**
     * 可配置的时间提供者，用于测试.
     */
    public static class MockClock implements Clock {

        private long time;

        public MockClock() {
            this(0);
        }

        public MockClock(Date date) {
            this.time = date.getTime();
        }

        public MockClock(long time) {
            this.time = time;
        }

        /**
         * 重新设置日期。
         */
        public void update(Date newDate) {
            time = newDate.getTime();
        }

        /**
         * 重新设置时间。
         */
        public void update(long newTime) {
            this.time = newTime;
        }

        /**
         * 滚动时间.
         */
        public void increaseTime(int millis) {
            time += millis;
        }

        /**
         * 滚动时间.
         */
        public void decreaseTime(int millis) {
            time -= millis;
        }

        @Override
        public Date getCurrentDate() {
            return new Date();
        }

        @Override
        public Long getCurrentTimeInMillis() {
            return time;
        }
    }

}
