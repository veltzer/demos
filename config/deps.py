import platform

packages=[
    # console fun
    "cowsay",
    "figlet",
    "sl",
    # xpath
    "libxml-xpath-perl",
    # scala
    # for scala(1), scalac(1), scalap(1), scaladoc(1) and more...
    "scala",
    # csharp
    "mono-runtime",
    "mono-devel",
    # android
    #"gcc-arm-linux-gnueabi
    # these are old versions which are no longer supported on ubuntu
    #"gcc-4.5-arm-linux-gnueabi
    #"gcc-4.4-arm-linux-gnueabi
    "gperf",
    "git",
    "gnupg",
    "flex",
    "bison",
    "gperf",
    "build-essential",
    "zip",
    "curl",
    "zlib1g-dev",
    "libc6-dev",
    "libncurses5-dev",
    "x11proto-core-dev",
    "libx11-dev",
    #"libreadline-gplv2-dev",
    "zlib1g-dev",
    "libgl1-mesa-dev",
    "g++-multilib",
    "tofrodos",
    # these I can"t find in ubuntu using apt-cache search. Fortunately, the build works:
    #"ia32-libs",
]
    
if hasattr(platform, "freedesktop_os_release"):
    desktop = platform.freedesktop_os_release()
    VERSION_ID = desktop["VERSION_ID"]
    # what version of urcu?
    opt_urcu_ver = None
    if VERSION_ID == "22.04":
        llvm_ver = 14
else:
    llvm_ver = 12 

packages.extend([
    # llvm
    f"llvm-{llvm_ver}",
    f"llvm-{llvm_ver}-doc",
    "llvm-runtime",
])
