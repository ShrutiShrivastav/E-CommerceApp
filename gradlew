<?xml version="1.0" encoding="utf-8"?>
<!-- Copyright (C) 2019 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->

<!--
This xml file allows customization of Android multiuser user types.
It is parsed by frameworks/base/services/core/java/com/android/server/pm/UserTypeFactory.java.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
++++++++++++++++++++++++++++++++++++++++   IMPORTANT NOTE   ++++++++++++++++++++++++++++++++++++++++
Although device customization is possible here, it is largely untested.
In particular, although this file allows new profile types to be created, and allows modifying the
number of managed profiles allowed on the device, the consequences of doing so is untested.
OEMs are advised to test very carefully any significant customization.
Further support is planned for later releases.
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Pre-defined (AOSP) user types can be customized and new types can be defined. The syntax is the
same in both cases.

Currently, only profiles (not full or system users) can be freely customized/defined.
Full users (i.e. non-system, non-profile) users cannot be defined, and the only property of them
that can be customized are the default-restrictions.
System users cannot be customized here; their default-restrictions must be set using
com.android.internal.R.array.config_defaultFirstUserRestrictions.

The following example modifies two AOSP user types (the FULL user android.os.usertype.full.SECONDARY
and the PROFILE user android.os.usertype.profile.MANAGED) and creates a new PROFILE user type
(com.example.profilename):

<user-types>
    <full-type name="android.os.usertype.full.SECONDARY" >
        <default-restrictions no_sms="true" />
    </full-type>

    <profile-type
        name='android.os.usertype.profile.MANAGED'
        max-allowed-per-parent='2'
        icon-badge='@android:drawable/ic_corp_icon_badge_case'
        badge-plain='@android:drawable/ic_corp_badge_case'
        badge-no-background='@android:drawable/ic_corp_badge_no_background' >
        <badge-labels>
            <item res='@android:string/managed_profile_label_badge' />
            <item res='@android:string/managed_profile_label_badge_2' />
        </badge-labels>
        <badge-colors>
            <item res='@android:color/profile_badge_1' />
            <item res='@android:color/profile_badge_2' />
        </badge-colors>
        <default-restrictions no_sms="true" no_outgoing_calls="true" />
    </profile-type>

    <profile-type
        name="com.example.profilename"
        max-allowed-per-parent="2" />
</user-types>

Mandatory attributes:
    name

Supported optional properties (to be used as shown in the example above) are as follows.
For profile and full users:
    default-restrictions (with values defined in UserRestrictionUtils.USER_RESTRICTIONS)
For profile users only:
    max-allowed-per-parent
    icon-badge
    badge-plain
    badge-no-background
    badge-labels
    badge-colors

See UserTypeFactory.java and UserTypeDetails.java for the meaning (and default values) of these
fields.

Any property that is specified overwrites the AOSP default. For example, if there is no
default-restrictions element, then the AOSP defaults for that user type will be used; however, if
there is a default-restrictions element, then the AOSP default restrictions will be completely
ignored and will instead obey this configuration.

If this file is updated, the properties of any pre-existing user types will be updated too.
Note, however, that default-restrictions refers to the restrictions applied at the time of user
creation; therefore, the active restrictions of any pre-existing users will not be updated.

-->
<user-types>
</user-types>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     