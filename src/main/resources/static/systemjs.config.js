/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
  System.config({
    paths: {
      // paths serve as alias
      'npm:': 'node_modules/'
    },
    // map tells the System loader where to look for things
    map: {
      // our app is within the app folder
      app: 'app',

      // angular bundles
      '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
      '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
      '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
      '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
      '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
      '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
      '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
      '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
      '@angular2-material/core': 'npm:@angular2-material/core/core.umd.js',
      '@angular2-material/button': 'npm:@angular2-material/button/button.umd.js',
      '@angular2-material/input': 'npm:@angular2-material/input/input.umd.js',
      '@angular2-material/sidenav': 'npm:@angular2-material/sidenav/sidenav.umd.js',

      // other libraries
      'rxjs': 'npm:rxjs',
      'ng2-bootstrap': 'npm:ng2-bootstrap',
      'angular2-fontawesome': 'node_modules/angular2-fontawesome',
      'angular2-modal': 'node_modules/angular2-modal',
      'angular2-modal/plugins/bootstrap': 'node_modules/angular2-modal/bundles',
      'ng2-select': 'node_modules/ng2-select',
      'ng2-datetime-picker': 'node_modules/ng2-datetime-picker/dist',
    },
    // packages tells the System loader how to load when no filename and/or no extension
    packages: {
      app: {
        main: './main.js',
        defaultExtension: 'js'
      },
      rxjs: {
        defaultExtension: 'js'
      },
      'angular2-fontawesome': { defaultExtension: 'js' },
      'angular2-modal': { defaultExtension: 'js', main: 'bundles/angular2-modal.umd' },
      'angular2-modal/plugins/bootstrap': { defaultExtension: 'js', main: 'angular2-modal.bootstrap.umd' },
      'ng2-select': { defaultExtension: 'js' },
      'ng2-datetime-picker': { main: 'ng2-datetime-picker.umd.js', defaultExtension: 'js' },
    }
  });
})(this);
