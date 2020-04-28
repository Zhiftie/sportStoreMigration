using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using IdentityServer4;
using IdentityServer4.Models;
using IdentityServer4.Test;

namespace IdentityServer
{
    public static class Config
    {
        public static IEnumerable<ApiResource> GetApiResourceses()
        {
            return new List<ApiResource>
            {
                new ApiResource("ocelot_gateway_api", "Ocelot.Gateway")
            };
        }

        public static IEnumerable<IdentityResource> GetIdentityResources()
        {
            return new List<IdentityResource>
            {
                new IdentityResources.OpenId(),
                new IdentityResources.Profile(),
            };
        }

        public static IEnumerable<Client> GetClientsResources()
        {
            return new List<Client>
            {
                new Client
                {
                    ClientId = "gateway_api_client",
                    ClientName = "Ocelot.Gateway.Client",
                    ClientSecrets = {new Secret("secret".Sha256())},

                    AllowedGrantTypes = GrantTypes.HybridAndClientCredentials,
                    AllowOfflineAccess = true,
                    RequireClientSecret = false,

                    RedirectUris = {"http://localhost:5002/signin-oidc"},
                    PostLogoutRedirectUris = {"http://localhost:5002/signout-callback-oidc"},

                    AllowedScopes =
                    {
                        IdentityServerConstants.StandardScopes.OpenId,
                        IdentityServerConstants.StandardScopes.Profile,
                        "ocelot_gateway_api"
                    },
                }
            };
        }

        public static List<TestUser> GetUsers()
        {
            return new List<TestUser>
            {
                new TestUser
                {
                    SubjectId = "1",
                    Username = "james",
                    Password = "password",
                    Claims = new List<Claim>
                    {
                        new Claim("name", "James Bond"),
                        new Claim("website", "MI6.org"),
                        new Claim("email", "007@secretagent.hush"),
                    }
                },
                new TestUser
                {
                    SubjectId = "2",
                    Username = "karl",
                    Password = "password",
                    Claims = new List<Claim>
                    {
                        new Claim("name", "Karl Pilkington"),
                        new Claim("website", "omegalul.com"),
                        new Claim("email", "karl@omegalul.com"),
                        new Claim("tenant", "Company2")
                    }
                }
            };
        }
    }
}
