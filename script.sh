#!/bin/bash

# Run Gradle tasks
./gradlew clean build releaseVersion publishToMavenLocal

# Load version from gradle.properties
J_SUPLA_VERSION=$(grep "^jSuplaVersion=" gradle.properties | cut -d'=' -f2)
echo "Version: $J_SUPLA_VERSION"
./gradlew noSnapshots

# Define target directories
mkdir -p "build/pl/grzeslowski/jSupla/server/$J_SUPLA_VERSION"
mkdir -p "build/pl/grzeslowski/jSupla/protocol/$J_SUPLA_VERSION"

SERVER_TARGET_DIR="build/pl/grzeslowski/jSupla/server/$J_SUPLA_VERSION"
# Copy server artifacts
cp server/build/libs/* "$SERVER_TARGET_DIR/"
cp server/build/publications/mavenJava/pom-default.xml       "$SERVER_TARGET_DIR/server-$J_SUPLA_VERSION.pom"
cp server/build/publications/mavenJava/pom-default.xml.asc   "$SERVER_TARGET_DIR/server-$J_SUPLA_VERSION.pom.asc"

# Generate checksums for server artifacts
for file in "$SERVER_TARGET_DIR"/*; do
    [[ "$file" == *.md5 || "$file" == *.sha1 ]] && continue
    md5sum "$file" | cut -d' ' -f1 > "$file.md5"
    sha1sum "$file" | cut -d' ' -f1 > "$file.sha1"
done

# Copy protocol artifacts
PROTOCOL_TARGET_DIR="build/pl/grzeslowski/jSupla/protocol/$J_SUPLA_VERSION"
cp protocol/build/libs/* "$PROTOCOL_TARGET_DIR/"
cp protocol/build/publications/mavenJava/pom-default.xml     "$PROTOCOL_TARGET_DIR/protocol-$J_SUPLA_VERSION.pom"
cp protocol/build/publications/mavenJava/pom-default.xml.asc "$PROTOCOL_TARGET_DIR/protocol-$J_SUPLA_VERSION.pom.asc"

# Generate checksums for protocol artifacts
for file in "$PROTOCOL_TARGET_DIR"/*; do
    [[ "$file" == *.md5 || "$file" == *.sha1 ]] && continue
    md5sum "$file" | cut -d' ' -f1 > "$file.md5"
    sha1sum "$file" | cut -d' ' -f1 > "$file.sha1"
done

# Go back to root and zip the "pl" directory
cd build || exit
zip -r "jSupla-server-$J_SUPLA_VERSION.zip" pl

TOKEN=$(echo "$OSSRH_USERNAME:$OSSRH_PASSWORD" | base64)

ID=$(curl --request POST \
  --header "Authorization: Bearer $TOKEN" \
  --form bundle="@jSupla-server-$J_SUPLA_VERSION.zip" \
  https://central.sonatype.com/api/v1/publisher/upload)
  
echo "Release ID=$ID"
curl --request POST \
  --header "Authorization: Bearer $TOKEN" \
  "https://central.sonatype.com/api/v1/publisher/status?id=$ID"

sleep 60

curl --request POST \
  --verbose \
  --header "Authorization: Bearer $TOKEN" \
  "https://central.sonatype.com/api/v1/publisher/deployment/$ID"
  
./gradlew tag
# Define the file path
FILE="build/tag.txt"

# Check if the file exists
if [ -f "$FILE" ]; then
  # Read the content of the file into the TAG variable
  TAG=$(cat "$FILE")
  echo "tag value: $TAG"
  echo "tag=$TAG" >> $GITHUB_OUTPUT

  echo "New tag $TAG"
  git tag $TAG
  echo "Pushing tag..."
  git push origin $TAG
else
  echo "File $FILE does not exist."
  exit 33
fi

./gradlew nextSnapshot
git add gradle.properties
git commit -m "Update jSuplaVersion to SNAPSHOT version in gradle.properties"

echo "Pushing master to origin..."
git push origin master 