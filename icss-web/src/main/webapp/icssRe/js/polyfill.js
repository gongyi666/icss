/**
 * Created by Kiva on 17/6/19.
 */
/** 兼容模块 **/
(function () {
    'use strict';
    if (!String.prototype.trim) {
        String.prototype.trim = function () {
            return this.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, '');
        };
    }
}());