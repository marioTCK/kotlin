/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.caches.resolve

import com.intellij.openapi.module.Module
import org.jetbrains.kotlin.analyzer.ResolverForModuleFactory
import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.config.KotlinFacetSettingsProvider
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.idea.caches.resolve.JsAnalyzerFacade
import org.jetbrains.kotlin.idea.caches.resolve.PlatformAnalysisSettings
import org.jetbrains.kotlin.js.resolve.JsPlatform
import org.jetbrains.kotlin.platform.impl.JsIdePlatformKind
import org.jetbrains.kotlin.platform.impl.isJavaScript

class JsPlatformKindResolution : IdePlatformKindResolution {
    override val kind get() = JsIdePlatformKind

    override val resolverForModuleFactory: ResolverForModuleFactory
        get() = JsAnalyzerFacade

    override fun isModuleForPlatform(module: Module): Boolean {
        val settings = KotlinFacetSettingsProvider.getInstance(module.project)
            .getInitializedSettings(module)
        return settings.platformKind.isJavaScript
    }

    override fun createBuiltIns(settings: PlatformAnalysisSettings, projectContext: ProjectContext): KotlinBuiltIns {
        return JsPlatform.builtIns
    }
}