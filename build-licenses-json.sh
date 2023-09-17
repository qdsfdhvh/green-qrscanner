./gradlew :shared:exportLibraryDefinitions -PaboutLibraries.exportPath=src/commonMain/resources/ -PaboutLibraries.exportVariant=release

# TODO: ios only support resources in pod library
cp shared/src/commonMain/resources/aboutlibraries.json iosAppCombine/src/commonMain/resources/aboutlibraries.json
