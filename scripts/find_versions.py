#!/usr/bin/env python3
import os
import csv
import xml.etree.ElementTree as ET

NS = {"m": "http://maven.apache.org/POM/4.0.0"}

def text(e):
    return e.text.strip() if e is not None and e.text else ""

def get_project_version(root):
    v = root.find("m:version", NS)
    if text(v):
        return text(v)
    pv = root.find("m:parent/m:version", NS)
    return text(pv)

def get_junit_versions(root):
    versions = set()
    for dep in root.findall(".//m:dependencies/m:dependency", NS):
        g = text(dep.find("m:groupId", NS))
        a = text(dep.find("m:artifactId", NS))
        v = text(dep.find("m:version", NS))

        if g.startswith("junit") or g.startswith("org.junit"):
            if v:
                versions.add(f"{g}:{a}:{v}")
            else:
                versions.add(f"{g}:{a}:<no-version>")
    return ";".join(sorted(versions))

def get_pit_version(root):
    paths = [
        ".//m:build/m:plugins/m:plugin",
        ".//m:build/m:pluginManagement/m:plugins/m:plugin",
    ]
    for path in paths:
        for plugin in root.findall(path, NS):
            g = text(plugin.find("m:groupId", NS))
            a = text(plugin.find("m:artifactId", NS))
            if g == "org.pitest" or "pitest" in a:
                v = text(plugin.find("m:version", NS))
                return v
    return ""

def get_maven_plugin_versions(root):
    """
    All plugins with groupId 'org.apache.maven.plugins',
    reported as artifactId:version (or <no-version>).
    """
    plugins = set()
    paths = [
        ".//m:build/m:plugins/m:plugin",
        ".//m:build/m:pluginManagement/m:plugins/m:plugin",
        ".//m:reporting/m:plugins/m:plugin",
    ]
    for path in paths:
        for plugin in root.findall(path, NS):
            g = text(plugin.find("m:groupId", NS))
            if g != "org.apache.maven.plugins":
                continue
            a = text(plugin.find("m:artifactId", NS))
            v = text(plugin.find("m:version", NS))
            if a:
                if v:
                    plugins.add(f"{a}:{v}")
                else:
                    plugins.add(f"{a}:<no-version>")
    return ";".join(sorted(plugins))

def main():
    root_dir = os.getcwd()

    with open("project_versions.csv", "w", newline="") as f:
        w = csv.writer(f)
        w.writerow([
            "folder_path",
            "pom_path",
            "project_version",
            "junit_versions",
            "pit_version",
            "maven_plugin_versions"
        ])

        for dirpath, dirnames, filenames in os.walk(root_dir):
            if "pom.xml" not in filenames:
                continue

            pom_path = os.path.join(dirpath, "pom.xml")
            try:
                tree = ET.parse(pom_path)
                root = tree.getroot()
            except Exception:
                continue

            project_version = get_project_version(root)
            junit_versions = get_junit_versions(root)
            pit_version = get_pit_version(root)
            maven_plugins = get_maven_plugin_versions(root)

            folder_rel = os.path.relpath(dirpath, root_dir)
            pom_rel = os.path.relpath(pom_path, root_dir)

            w.writerow([
                folder_rel,
                pom_rel,
                project_version,
                junit_versions,
                pit_version,
                maven_plugins
            ])

if __name__ == "__main__":
    main()
