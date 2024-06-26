/*
 * Piwigo for Android
 * Copyright (C) 2016-2017 Piwigo Team http://piwigo.org
 * Copyright (C) 2018      Raphael Mack http://www.raphael-mack.de
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
 */

package org.piwigo;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import org.piwigo.helper.DialogHelper;
import org.piwigo.helper.NetworkHelper;
import org.piwigo.helper.NotificationHelper;
import org.piwigo.internal.di.component.ApplicationComponent;
import org.piwigo.internal.di.component.BindingComponent;
import org.piwigo.internal.di.component.DaggerApplicationComponent;
import org.piwigo.internal.di.component.DaggerBindingComponent;
import org.piwigo.internal.di.module.ApplicationModule;
import org.piwigo.io.PreferencesRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;


public class PiwigoApplication extends Application implements HasAndroidInjector {

    @Inject DispatchingAndroidInjector<Object> androidInjector;
    @Inject PreferencesRepository preferencesRepository;

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();

        new NetworkHelper();
        new NotificationHelper(getApplicationContext());
        new DialogHelper();
        initializeDependencyInjection();

        applyColorPalette(preferencesRepository.getString(PreferencesRepository.KEY_PREF_COLOR_PALETTE));

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void initializeDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

        BindingComponent bindingComponent = DaggerBindingComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        DataBindingUtil.setDefaultComponent(bindingComponent);
    }

    public void applyColorPalette(String colorPalette)
    {
        switch (colorPalette) {
            case "light":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "dark":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "auto":
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
        }
    }

    /**
     * Returns an {@link AndroidInjector}.
     */
    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

}